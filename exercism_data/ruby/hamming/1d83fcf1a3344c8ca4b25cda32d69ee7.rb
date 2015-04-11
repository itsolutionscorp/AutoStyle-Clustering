class TestCount
	def initialize
		4.times do 	
			@items << Item.new(:prop => true)			
		end
		3.times do 	
			@items << Item.new(:prop => false)			
		end
	end
	def display
		p "#{@items.count(&:prop)}"
	end
end

class Item
	attr_accessible :prop
end

TestCount.new.display
