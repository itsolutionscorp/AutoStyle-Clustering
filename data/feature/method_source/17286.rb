def combine_anagrams(words)
  @a = Array.new
  @b = Array.new
  @a = ([] + words)
  @b = ([] + words)
  @rtn = []
  @temp = Array.new
  @a.each_with_index do |wor, i|
    @b.each_with_index { |w, l| (@temp << wor) if (wor == w) }
    if (not @temp.empty?) then
      @b.each_with_index do |wor1, j|
        if (wor.downcase.split("").sort.join == wor1.downcase.split("").sort.join) and (wor != wor1) then
          (@temp << wor1)
        end
      end
      (@rtn << @temp)
      @b = (@b - @temp)
      @test = @b if (i == 1)
      @temp = Array.new
    end
  end
  return @rtn
end