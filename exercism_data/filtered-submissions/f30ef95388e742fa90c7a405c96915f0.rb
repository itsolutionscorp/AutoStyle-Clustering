class Hamming
  def compute(string1, string2)
    result = 0
    if string1.size <= string2.size
      @short = string1.split(//)
      @long = string2.split(//)
    else
      @short = string2.split(//)
      @long = string1.split(//)
    end
    @short.each_index{|i| result += 1 if @short[i] != @long[i]}
    result
  end
end
