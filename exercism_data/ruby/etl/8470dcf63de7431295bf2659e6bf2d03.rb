module ETL
  def self.transform old
    old.reduce({}) do |prices,(price,letters)|
      prices.merge transform_one_record(price, letters)
    end
  end

  def self.transform_one_record price, letters
    Hash[ letters.map { |letter| [ letter.downcase, price ] } ]
  end
end
