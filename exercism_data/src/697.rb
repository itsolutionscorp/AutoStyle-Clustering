def compute(first,second)
      first,second=[first,second].sort_by(&:size)
      first.bytes.zip(second.bytes).map{|x| true if x.first!=x.last}.compact.size
    end