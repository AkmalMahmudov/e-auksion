//package uz.akmal.e_auksion.ui.adapters
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseExpandableListAdapter
//import uz.akmal.e_auksion.R
//
//
//class ExpandableAdapter(
//    private val context: Context,
////    private val subItem: List<List<OnlineCardResponse>>,
//    private val listener: ExpandableAdapterListener
//) : BaseExpandableListAdapter() {
//
//
//    override fun getGroupCount(): Int {
//        if(subItem[subItem.lastIndex].isNotEmpty()){
//            return subItem.size
//        }
//        return subItem.size-1
//    }
//
//    override fun getChildrenCount(groupPosition: Int): Int = subItem[groupPosition].size
//
//    override fun getGroup(groupPosition: Int): String {
//        return subItem[groupPosition][0].type_name
//    }
//
//    override fun getChild(groupPosition: Int, childPosition: Int): String {
//        return subItem[groupPosition][childPosition].name
//    }
//
//    override fun getGroupId(groupPosition: Int): Long {
//        return groupPosition.toLong()
//    }
//
//    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()
//
//    override fun hasStableIds(): Boolean {
//        return true
//    }
//
//    @SuppressLint("InflateParams")
//    override fun getGroupView(
//        groupPosition: Int,
//        isExpanded: Boolean,
//        convertView: View?,
//        parent: ViewGroup?
//    ): View {
//
//        var view = convertView
//        if (view == null) {
//            val inflater =
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            view = inflater.inflate(R.layout.item_exp, null)
//        }
//
//        val title = view!!.findViewById<TextView>(R.id.card_name)
//        if (subItem[groupPosition].isNotEmpty()) {
//
//            title.text = subItem[groupPosition][0].type_name.uppercase()
//
//            view.findViewById<RelativeLayout>(R.id.item_online_card).setOnClickListener {
//                listener.onParentClicksItem(groupPosition, isExpanded)
//            }
//
//            when (subItem[groupPosition][0].type) {
//                1 -> Glide.with(view).load(R.drawable.ic_humo_white)
//                    .into(view.findViewById(R.id.card_logo))
//                2 -> Glide.with(view).load(R.drawable.ic_uzcard_white)
//                    .into(view.findViewById(R.id.card_logo))
//                3 -> Glide.with(view).load(R.drawable.ic_visa_white)
//                    .into(view.findViewById(R.id.card_logo))
//                4 -> Glide.with(view).load(R.drawable.ic_union_pay_01)
//                    .into(view.findViewById(R.id.card_logo))
//                else -> Glide.with(view).load(R.drawable.ordercardd)
//                    .into(view.findViewById(R.id.card_logo))
//            }
//            if (isExpanded) {
//                view.icon_arrow.setImageResource(R.drawable.ic_arrow_up)
//
//            } else {
//                view.icon_arrow.setImageResource(R.drawable.ic_arrow_down)
//            }
//        }
//
//
//
//        return view
//    }
//
//
//    @SuppressLint("InflateParams", "SetTextI18n")
//    override fun getChildView(
//        groupPosition: Int,
//        childPosition: Int,
//        isLastChild: Boolean,
//        convertView: View?,
//        parent: ViewGroup?
//    ): View {
//        var view = convertView
//        if (view == null) {
//            val inflater =
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            view = inflater.inflate(R.layout.item_list_online_card, null)
//        }
//        val subItems = view!!.findViewById<TextView>(R.id.card_title)
//        subItems.text = subItem[groupPosition][childPosition].name
//
//        val cardIssue = view.findViewById<TextView>(R.id.card_issue)
//        cardIssue.text =
//            "${view.context.getString(R.string.card_issue)} ${subItem[groupPosition][childPosition].reg_sum}"
//
//        val deliveryCard = view.findViewById<TextView>(R.id.delivery_card)
//        deliveryCard.text =
//            "${view.context.getString(R.string.delivery_card)} ${subItem[groupPosition][childPosition].ship_sum}"
//
//        val cardService = view.findViewById<TextView>(R.id.card_service)
//        cardService.text =
//            "${view.context.getString(R.string.card_service)} ${subItem[groupPosition][childPosition].service_cost}"
//
//        val transaction = view.findViewById<TextView>(R.id.transaction_processing)
//        transaction.text =
//            "${view.context.getString(R.string.transaction_processing)} ${subItem[groupPosition][childPosition].transaction}"
//
//        val cardValidity = view.findViewById<TextView>(R.id.card_validity)
//        cardValidity.text =
//            "${view.context.getString(R.string.card_validity)} ${subItem[groupPosition][childPosition].validate} ${
//                view.context.getString(R.string.year_short)
//            }"
//
//        Glide.with(view).load(subItem[groupPosition][childPosition].src)
//            .into(view.findViewById(R.id.card_logo))
//
//        val btnSelect = view.findViewById<Button>(R.id.next_button)
//        btnSelect.setOnClickListener {
//            listener.onClicksItem(subItem[groupPosition][childPosition])
//        }
//
//        return view
//    }
//
//    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
//        return false //Allowing child to be clicked
//    }
//
//    override fun areAllItemsEnabled(): Boolean {
//        return false
//    }
//
//    interface ExpandableAdapterListener {
//        fun onClicksItem(onlineCardData: OnlineCardResponse)
//        fun onParentClicksItem(position: Int, isExpandable: Boolean)
//    }
//
//}