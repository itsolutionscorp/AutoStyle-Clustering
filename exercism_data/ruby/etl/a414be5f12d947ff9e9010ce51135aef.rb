module ETL
  module_function
  def transform(data)
    data.inject({}) {|new_data, point_value_array|
      point_value_array.last.each {|letter|
        new_data[letter.downcase] = point_value_array.first
      }
      new_data
    }
  end
end
