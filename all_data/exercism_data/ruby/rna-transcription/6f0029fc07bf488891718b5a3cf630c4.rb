class Complement
	class << self
		def of_dna(d)
			table = { 'G'=>'C','C'=>'G', 'T'=>'A', 'A'=>'U' }
			arr = d.chars
			arr.each_with_index{|x, i| arr[i] = table[x] }
			arr.join
		end

		def of_rna(d)
			table = { 'G'=>'C','C'=>'G', 'A'=>'T', 'U'=>'A' }
			arr = d.chars
			arr.each_with_index{|x, i| arr[i] = table[x] }
			arr.join
		end
	end
end
#* `T` -> `A`
#* `A` -> `U`
