class Hamming
	def initialize
	end

	def self.compute(a,b)
		arrya = a.split("")
		arryb = b.split("")
		if arrya.count > arryb.count
			arrya.delete_at(-1)
		elsif arryb.count > arrya.count
			arryb.delete_at(-1)
		end
		arryc = arrya.zip(arryb).map { |a,b| a != b  }
		cnt = arryc.count {|x| x == true}
		return cnt
	end

end
