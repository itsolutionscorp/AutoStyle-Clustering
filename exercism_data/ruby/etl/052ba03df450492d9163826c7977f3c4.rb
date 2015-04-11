class ETL
  def self.transform(old_format)
    output = {}
    old_format.keys.each do |points|
      old_format[points].each do |letter|
        output[letter.downcase] = points
      end
    end
    output
  end
end
