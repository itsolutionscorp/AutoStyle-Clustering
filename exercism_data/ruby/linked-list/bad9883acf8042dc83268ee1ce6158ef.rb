class Deque

	Element = Struct.new(:datum, :prev, :next)

	def initialize
		clear
	end

	def push(datum)
		itm = Element.new(datum)
		if empty?
			@first = @last = itm
		else
			@last.next = itm
			itm.prev = @last
			@last = itm
		end
		self
	end
	
	def pop
		return nil if empty?
		datum = @last.datum
		if only_one?
			clear
		else
			@last.prev.next = nil
			@last = @last.prev
		end
		datum
	end
	
	def shift
		return nil if empty?
		datum = @first.datum
		if only_one?
			clear
		else
			@first.next.prev = nil
			@first = @first.next
		end
		datum
	end
	
	def unshift(datum)
		itm = Element.new(datum)
		if empty?
			@first = @last = itm
		else
			@first.prev = itm
			itm.next = @first
			@first = itm
		end
		self
	end

private
	def clear
		@first = @last = nil
	end

	def empty?
		@first.nil? && @last.nil?
	end
	
	def only_one?
		(@first == @last) && !empty?
	end
end
