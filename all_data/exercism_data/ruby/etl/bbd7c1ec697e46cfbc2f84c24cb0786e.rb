module ETL
  def self.transform old_data
    old_data.each_with_object({}){|old_record, new_data| 
      score, letters = old_record
      letters.each{|l| new_data[l.downcase] = score }
    }
  end
end
