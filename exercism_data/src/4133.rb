def compute(string1,string2)
  first_array = string1.split(//)
  second_array = string2.split(//)

  if first_array.length > second_array.length
  first_array = first_array.slice(0..-2)
  elsif second_array.length > first_array.length
    second_array = second_array.slice(0..2)
end
  first_array
  second_array

  zipped_array = first_array.zip second_array

  distance = 0

  zipped_array.each do |x|
    if x[0] != x[1]
      distance += 1
    end

  end
  distance

end