class ETL
  def self.transform old_list
    old_list.each_with_object({}) do |(score, letters), new_list|
      letters.each { |l| new_list[l.downcase] = score }
    end
  end
end
