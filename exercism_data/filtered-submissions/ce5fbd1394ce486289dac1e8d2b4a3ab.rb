def compute(first,second)
  first_array = first.chars
  second_array = second.chars
  zipped = first_array.zip(second_array)
  zipped.select{|x| x[0] != x[1]}.count
end