module ETL

  def transform( data )
    new_data = []
    data.each do |key, value|
      value.each do |v|
        new_data << [ v.downcase, key ]
      end
    end
    Hash[ new_data ]
  end
end
