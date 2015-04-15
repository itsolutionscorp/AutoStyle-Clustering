class Year
    def self.leap?(años)
        if(años%400==0 ||(años%4==0 && años%100!=0)) 
            return  true
        end
    end
end 
