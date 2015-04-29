class Year
    def self.leap?(anno)
    	if (anno%100==0 && anno%400==0)
            return true
        end
        if (anno%4==0 && anno%100!=0)
            return true
        end        
    end
end
