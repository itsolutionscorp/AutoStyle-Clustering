def compute(arg_one,arg_two)
    hamming=0
    arg_one.split("").zip(arg_two.split("")).each do |compare|
      hamming=hamming+1 if compare[0]!=compare[1]
    end
    return hamming
  end