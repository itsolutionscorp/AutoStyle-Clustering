class ETL
  def self.transform legacy
    new_format = Hash.new
    legacy.each do |value,chars|
      chars.each do |char|
        new_format[char.downcase] = value
      end
    end
    new_format
  end
end
