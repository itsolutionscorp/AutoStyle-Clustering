class Say

	def initialize(number)
		@number = number.to_s.split(//).map {|n| n.to_i unless n == '-'}
	end
	
	def in_english
		english_number = ''
		raise ArgumentError.new if @number.length > 12 || ( @number[0] == '-' && @number.length >= 12 )
		
		#HUNDRED BILLIONS		
		unless @number.length <= 11
		english_number += 	case @number.take(@number.length - 11).last
								when 0
									''
								when 1
									'one'
								when 2
									'two'
								when 3
									'three'
								when 4
									'four'
								when 5
									'five'
								when 6
									'six'
								when 7
									'seven'
								when 8
									'eight'
								when 9
									'nine'
								else 
									raise ArgumentError.new
							end
		english_number += ' hundred ' unless @number.take(@number.length - 11).last == 0				
		end
			
		#TEN BILLIONS
		unless @number.length <= 10
		english_number += 	case @number.take(@number.length - 10).last
								when 0
										''
								when 1
									if @number.last.to_i == 0
										'ten'
									elsif @number.last.to_i == 1
										'eleven'
									elsif @number.last.to_i == 2
										'twelve'
									elsif @number.last.to_i == 3
										'thirteen'
									elsif @number.last.to_i == 4
										'fourteen'
									elsif @number.last.to_i == 5
										'fifteen'
									elsif @number.last.to_i == 6
										'sixteen'
									elsif @number.last.to_i == 7
										'seveteen'
									elsif @number.last.to_i == 8
										'eighteen'
									elsif @number.last.to_i == 9
										'nineteen'
									end
								when 2
									'twenty'
								when 3
									'thirty'
								when 4
									'fourty'
								when 5
									'fifty'
								when 6
									'sixty'
								when 7
									'seventy'
								when 8
									'eighty'
								when 9
									'ninety'
								else 
									raise ArgumentError.new
							end
		english_number += '-' unless @number.take(@number.length - 10).last == 0 || @number[@number.length - 2] == 0 || @number[@number.length - 2] == 1 
		end
		
		#BILLIONS
		unless @number.length <= 9
		english_number += 	case @number.take(@number.length - 9).last
								when 0
									''
								when 1
									'one'
								when 2
									'two'
								when 3
									'three'
								when 4
									'four'
								when 5
									'five'
								when 6
									'six'
								when 7
									'seven'
								when 8
									'eight'
								when 9
									'nine'
								else 
									raise ArgumentError.new
							end
		english_number += ' billion ' unless @number.take(@number.length - 9).last == 0
		end
		
		#HUNDRED MILLIONS		
		unless @number.length <= 8
		english_number += 	case @number.take(@number.length - 8).last
								when 0
									''
								when 1
									'one'
								when 2
									'two'
								when 3
									'three'
								when 4
									'four'
								when 5
									'five'
								when 6
									'six'
								when 7
									'seven'
								when 8
									'eight'
								when 9
									'nine'
								else 
									raise ArgumentError.new
							end
		english_number += ' hundred ' unless @number.take(@number.length - 8).last == 0				
		end
			
		#TEN MILLIONS
		unless @number.length <= 7
		english_number += 	case @number.take(@number.length - 7).last
								when 0
										''
								when 1
									if @number.last.to_i == 0
										'ten'
									elsif @number.last.to_i == 1
										'eleven'
									elsif @number.last.to_i == 2
										'twelve'
									elsif @number.last.to_i == 3
										'thirteen'
									elsif @number.last.to_i == 4
										'fourteen'
									elsif @number.last.to_i == 5
										'fifteen'
									elsif @number.last.to_i == 6
										'sixteen'
									elsif @number.last.to_i == 7
										'seveteen'
									elsif @number.last.to_i == 8
										'eighteen'
									elsif @number.last.to_i == 9
										'nineteen'
									end
								when 2
									'twenty'
								when 3
									'thirty'
								when 4
									'fourty'
								when 5
									'fifty'
								when 6
									'sixty'
								when 7
									'seventy'
								when 8
									'eighty'
								when 9
									'ninety'
								else 
									raise ArgumentError.new
							end
		english_number += '-' unless @number.take(@number.length - 7).last == 0 || @number[@number.length - 2] == 0 || @number[@number.length - 2] == 1 
		end
		
		#MILLIONS
		unless @number.length <= 6
		english_number += 	case @number.take(@number.length - 6).last
								when 0
									''
								when 1
									'one'
								when 2
									'two'
								when 3
									'three'
								when 4
									'four'
								when 5
									'five'
								when 6
									'six'
								when 7
									'seven'
								when 8
									'eight'
								when 9
									'nine'
								else 
									raise ArgumentError.new
							end
		english_number += ' million ' unless @number.take(@number.length - 6).last == 0
		end
		
		#HUNDRED THOUSANDS		
		unless @number.length <= 5
		english_number += 	case @number.take(@number.length - 5).last
								when 0
									''
								when 1
									'one'
								when 2
									'two'
								when 3
									'three'
								when 4
									'four'
								when 5
									'five'
								when 6
									'six'
								when 7
									'seven'
								when 8
									'eight'
								when 9
									'nine'
								else 
									raise ArgumentError.new
							end
		english_number += ' hundred ' unless @number.take(@number.length - 5).last == 0				
		end
			
		#TEN THOUSANDS
		unless @number.length <= 4
		english_number += 	case @number.take(@number.length - 4).last
								when 0
										''
								when 1
									if @number.last.to_i == 0
										'ten'
									elsif @number.last.to_i == 1
										'eleven'
									elsif @number.last.to_i == 2
										'twelve'
									elsif @number.last.to_i == 3
										'thirteen'
									elsif @number.last.to_i == 4
										'fourteen'
									elsif @number.last.to_i == 5
										'fifteen'
									elsif @number.last.to_i == 6
										'sixteen'
									elsif @number.last.to_i == 7
										'seveteen'
									elsif @number.last.to_i == 8
										'eighteen'
									elsif @number.last.to_i == 9
										'nineteen'
									end
								when 2
									'twenty'
								when 3
									'thirty'
								when 4
									'fourty'
								when 5
									'fifty'
								when 6
									'sixty'
								when 7
									'seventy'
								when 8
									'eighty'
								when 9
									'ninety'
								else 
									raise ArgumentError.new
							end
		english_number += '-' unless @number.take(@number.length - 4).last == 0 || @number[@number.length - 2] == 0 || @number[@number.length - 2] == 1 
		end
		
		#THOUSANDS
		unless @number.length <= 3
		english_number += 	case @number.take(@number.length - 3).last
									when 0
										''
									when 1
										'one'
									when 2
										'two'
									when 3
										'three'
									when 4
										'four'
									when 5
										'five'
									when 6
										'six'
									when 7
										'seven'
									when 8
										'eight'
									when 9
										'nine'
									else 
										raise ArgumentError.new
								end
		english_number += ' thousand ' unless @number.take(@number.length - 3).last == 0
		end
		
		#HUNDREDS
		unless @number.length <= 2
		english_number += 	case @number.take(@number.length - 2).last
								when 0
									''
								when 1
									'one'
								when 2
									'two'
								when 3
									'three'
								when 4
									'four'
								when 5
									'five'
								when 6
									'six'
								when 7
									'seven'
								when 8
									'eight'
								when 9
									'nine'
								else 
									raise ArgumentError.new
							end
		english_number += ' hundred ' unless @number.length == 1 || @number.take(@number.length - 2).last == 0						
		end
		
		#TENTHS
		unless @number.length <= 1
		english_number += 	case @number.take(@number.length - 1).last
								when 0
										''
								when 1
									if @number.last.to_i == 0
										'ten'
									elsif @number.last.to_i == 1
										'eleven'
									elsif @number.last.to_i == 2
										'twelve'
									elsif @number.last.to_i == 3
										'thirteen'
									elsif @number.last.to_i == 4
										'fourteen'
									elsif @number.last.to_i == 5
										'fifteen'
									elsif @number.last.to_i == 6
										'sixteen'
									elsif @number.last.to_i == 7
										'seveteen'
									elsif @number.last.to_i == 8
										'eighteen'
									elsif @number.last.to_i == 9
										'nineteen'
									end
								when 2
									'twenty'
								when 3
									'thirty'
								when 4
									'forty'
								when 5
									'fifty'
								when 6
									'sixty'
								when 7
									'seventy'
								when 8
									'eighty'
								when 9
									'ninety'
								else 
									raise ArgumentError.new
							end
		english_number += '-' unless @number.last == 0 || @number[@number.length - 2] == 0 || @number[@number.length - 2] == 1 
		end
		
		#ONES
		unless @number.take(@number.length - 1).last == 1
		english_number += 	case @number.last
								when 0
									@number.length == 1 ? 'zero' : '' 
								when 1
									'one'
								when 2
									'two'
								when 3
									'three'
								when 4
									'four'
								when 5
									'five'
								when 6
									'six'
								when 7
									'seven'
								when 8
									'eight'
								when 9
									'nine'
							end
		end
		english_number = english_number.chomp(' ').gsub(/ {2,}/, ' ').gsub(/^ /, '')
	end
end
