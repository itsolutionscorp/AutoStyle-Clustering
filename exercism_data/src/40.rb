def compute(strandA,strandB)
    strandA.chars.zip(strandB.chars).select{|a,b| a!=b and (a.nil? || b.nil?) ==false }.count
  end