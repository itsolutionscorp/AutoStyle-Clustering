class Hamming
  def compute(input1, input2)
    @minlength = [input1.length, input2.length].min
    @maxlength = [input1.length, input2.length].max
    @to_ignore = 0
    
    if @maxlength > @minlength
      @to_ignore= @maxlength - @minlength
    end

    @i = 0
    @j = @maxlength - @to_ignore
    @result = 0
    
    while @i < @j do
      if input1[@i] != input2[@i]
        @result = @result + 1
      end
      @i = @i + 1
    end
    return @result
  end
end
