class Series

  def initialize(str)
    @str = str
  end

  def slices(num)
    #raise error if slice request is higher than length
    raise ArgumentError if num > @str.length

    results = []
    @str.each_char.with_index do |letter, i|
      temp = [] # store temp results
      num.times { |j| temp << @str[i+j].to_i if @str[i+j] }
      results << temp if temp.length >= num #only append if meets slice count
    end
    results
  end

end
