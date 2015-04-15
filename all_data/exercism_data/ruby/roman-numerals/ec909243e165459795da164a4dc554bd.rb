class Fixnum

  def to_roman
    self.to_s.scan(/\d/).collect!{|e| e.to_i}.reverse.each.with_index.inject("") {|str, (d, i)| evaluate(d, i) + str }
  end

  def evaluate(data, i)
    case
  	    when data < 4
          get_character(i, :ones) * data
  	    when data == 4
      	  get_character(i, :ones) + get_character(i, :fives)
  	    when (data >= 5 and data < 9)
    	  get_character(i, :fives) + (get_character(i, :ones) * (data - 5))
  	    when (data == 9)
    	  get_character(i, :ones) + get_character(i+1, :ones)
	  end
  end

  def get_character(i, j)
  	chars = {:ones => ["I", "X", "C", "M"],  :fives => ["V", "L", "D"]}
  	chars[j][i]
  end

  private :evaluate, :get_character

end
