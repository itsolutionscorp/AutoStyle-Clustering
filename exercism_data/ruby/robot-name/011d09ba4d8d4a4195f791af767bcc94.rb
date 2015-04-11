class Robot
  
  def initialize
    @result = String.new
  end

  def name
    if @result.empty?
      str_arr = ('a'..'z').to_a + ('A'..'Z').to_a
      num_arr = (0..9).to_a.map(&:to_s)
      @result << str_arr.shuffle[0..1].join << num_arr.shuffle[0..2].join
    end
    @result
  end

  def reset
    @result = ""
  end

end
