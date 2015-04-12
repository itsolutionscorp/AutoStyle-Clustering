class Hamming 
  class << self
    def compute(x, y)
    	count = 0
    	x.split(//).each_with_index do |e, i|
    		count+=1 if e!=y[i]
    	end
    	count
    end
  end
end
