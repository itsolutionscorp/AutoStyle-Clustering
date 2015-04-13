def compute(value1, value2)
    @value1_array = value1.scan(/./)
    @value2_array = value2.scan(/./)
    @counter=0
    @min_index=0
    @looper=0

    if
     @value1_array.count <= @value2_array.count
     @min_index=@value1_array.count
    else
     @min_index=@value2_array.count
    end

      while @looper<@min_index do

        if @value2_array[@looper]!=@value1_array[@looper]
            @counter += 1
        end
        @looper += 1
      end

    @counter
  end