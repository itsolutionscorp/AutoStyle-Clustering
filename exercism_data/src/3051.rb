class Hamming
    def compute(first, second)
	first.each_char.with_index.count do |element, index|
	    second[index] != element
	end
    end
end
