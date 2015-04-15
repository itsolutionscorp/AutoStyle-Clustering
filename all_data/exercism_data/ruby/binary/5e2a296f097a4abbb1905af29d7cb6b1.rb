class Binary
	attr_accessor:to_decimal
    def initialize(s)
    	@to_decimal = 0 
    	s.reverse.split("").each_with_index{ |b, pos| @to_decimal += (b.to_i * 2**pos) } if s.gsub(/[01]/,"").length==0
    end
end
