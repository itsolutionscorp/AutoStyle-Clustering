class Element
	attr_reader :datum
	attr_accessor :next
	def initialize(value, next_item = nil)
		@datum = value
		@next = next_item
	end
	
	def self.to_a(element)
		return [] if element.nil?
		[element.datum] + self.to_a(element.next)
	end
	
	def self.from_a(ary)
		items = ary.is_a?(Array) ? ary : [*ary]
		return nil if items.empty?
		new(items.first, self.from_a(items.drop(1)))
	end
	
	def to_a
		self.class.to_a(self)
	end
	
	def reverse
		item = self.dup
		item.next = nil
		if self.next.nil?
			return item
		else
			head = self.next.reverse
			head.next = item
			return head
		end
	end
end
