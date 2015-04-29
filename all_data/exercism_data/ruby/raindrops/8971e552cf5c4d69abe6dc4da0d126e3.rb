require 'prime'
class Raindrops
  def self.convert(i)
  	sounds=""
  	num=i
  	Prime.each(100) do |prime|
		  c=true
		  num_met=true
		  while c == true
			  if( i % prime == 0 )				
					i=i/prime
					if num_met
				  	sounds+=sounds(prime)
				  	num_met = false
				  end					
				else				
				   c=false
				end
			end			
		end
		if(sounds.length>1)
			return sounds		
		else
			return num.to_s
		end
  end
  def self.sounds(i)
    case i
		when 3
		  return 'Pling'
		when 5
		  return 'Plang'
		when 7
		  return 'Plong'
		else
		  return ""
		end
  end
end
