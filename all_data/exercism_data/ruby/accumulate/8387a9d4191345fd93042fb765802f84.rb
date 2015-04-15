class Array
  def accumulate
   if block_given?
     i = 0
     new_array = []

     while i < size
       result = yield(self[i])
       new_array << result
       i += 1
     end
   end

   new_array
  end
end
