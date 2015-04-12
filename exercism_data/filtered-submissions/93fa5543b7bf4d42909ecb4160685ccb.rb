def compute(comp_strand, ori_strand)
    return_val = 0
    zipped_up = comp_strand.split(//).zip(ori_strand.split(//))
    zipped_up.each{|k,v| return_val += 1 if ((k != v) && (!k.nil? && !v.nil?))}
    return_val
  end