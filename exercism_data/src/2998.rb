def compute(value1, value2)
    @value1_array = value1.scan(/./)
    @value2_array = value2.scan(/./)
    @counter=0
    @min_index=0
    @looper=0
#set min index to the number of elements in the smallest array
    if
     @value1_array.count <= @value2_array.count
     @min_index=@value1_array.count 
    else
     @min_index=@value2_array.count
    end
#loop through all variables until min index is hit 
      while @looper<@min_index do
#if there was no match increase the counter variable
        if @value2_array[@looper]!=@value1_array[@looper]
            @counter += 1
        end    
        @looper += 1
      end 
#display results
    @counter
  end