class Hamming
  def self.compute(arg1, arg2)
  	diff_letter_count = 0
  	a = arg1.split("")
  	b = arg2.split("")
  	zipped = a.zip(b)
  	zipped.each do |arr|
      arr[0] == arr[1] ? diff_letter_count += 0 : diff_letter_count += 1
  	end
  	diff_letter_count
  end
end
