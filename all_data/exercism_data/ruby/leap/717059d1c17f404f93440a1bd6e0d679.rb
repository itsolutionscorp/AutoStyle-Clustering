class Year
		def self.leap?(ayear)
      if ayear%400 == 0
        return true
      elsif ayear%100 == 0
        return false
      elsif ayear%4 == 0
        return true
      else 
        return false
      end
		end
end
