package com.example.mywechat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//public class adapter extends RecyclerView.Adapter <adapter.myviewholder>{
//    private List<String> mlist=new ArrayList<>();
//    private Context context;
//    private View inflater;
//    private  static  final String tag=adapter.class.getSimpleName();
//    private int expandedPosition=1;
//    private myviewholder mViewHolder;
//
//    public adapter(Context context) {//,List<String> list
//        this.context=context;
////        this.list=list;
//    }
//    public void setExpandCollapseDataList(List<String> list){
//        mlist=list;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public adapter.myviewholder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        inflater= LayoutInflater.from(context).inflate(R.layout.tab01,viewGroup,false);
//        myviewholder myviewholder=new myviewholder(inflater);
//        return myviewholder;
//    }
//
//    @Override
//    public void onBindViewHolder(final adapter.myviewholder myviewholder, final int j) {
//
//        myviewholder.textviewparent.setText(mlist.get(j));
//        myviewholder.textviewchild.setText(mlist.get(j+15));
//        final boolean isExpanded=j==expandedPosition;
//        myviewholder.rlChild.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
//        myviewholder.rlParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mViewHolder != null) {
//                    mViewHolder.rlChild.setVisibility(View.GONE);
//                    notifyItemChanged(expandedPosition);
//                }
//                expandedPosition = isExpanded ? -1 : myviewholder.getAdapterPosition();
//                mViewHolder = isExpanded ? null : myviewholder;
//                notifyItemChanged(myviewholder.getAdapterPosition());
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
////        return 30;
//        return mlist == null ? 0 : mlist.size()/2;
//    }
//
//    class myviewholder extends RecyclerView.ViewHolder{
//        //        private ImageView imageview;
////        TextView textView;
////        public  myviewholder(View itemView){
////            super(itemView);
////            imageview=itemView.findViewById(R.id.imageview);
////            textView=itemView.findViewById(R.id.textView2);
////        }
//
//        RelativeLayout rlParent, rlChild;
//        TextView textviewparent, textviewchild;
//        public myviewholder(View itemView) {
//            super(itemView);
//            rlParent = itemView.findViewById(R.id.rl_parent);
//            rlChild = itemView.findViewById(R.id.rl_child);
//            textviewparent = itemView.findViewById(R.id.textViewparent);
//            textviewchild = itemView.findViewById(R.id.textViewchild);
//        }
//    }
//}
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private List<UserAccount>userAccounts;
    private Context mContext;

    public MyAdapter(Context context,List<UserAccount>userAccounts){
        mContext=context;
        this.userAccounts=userAccounts;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.itemview,null);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        ((TextView)myHolder.finViewById(R.id.tv_username)).setText(userAccounts.get(i).getUsername());
        ((TextView)myHolder.finViewById(R.id.tv_password)).setText(userAccounts.get(i).getPassword());
    }

    @Override
    public int getItemCount() {
        return userAccounts.size();
    }
}

