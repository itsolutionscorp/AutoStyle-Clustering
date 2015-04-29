class ETL
  def self.transform(old)
    old.each_with_object({}) do |(number, letter), transform|
      letter.each do |l|
        transform[l.downcase] = number
      end
    end
  end
end
