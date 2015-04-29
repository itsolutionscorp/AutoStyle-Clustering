class ETL
  def self.transform(old_data)
    old_data.each_with_object({}) do |list,store|
      list[1].each do |letter|
        store[letter.downcase] = list[0]      
      end
    end
  end
end
