class ETL

  def ETL.transform input
    output = {}
    input.each do
      # iterate through each key/value pair where the value is an array
      # eg.. { "foo" = > ["BAR"] } 
      | key, value |
        value.each do 
        #pull each element from the value array to be a key in output hash
        #in the above example that would ["BAR"]
        #where the value will be the key from the input hash
        #which will be { "bar" => "foo" }
          | element | output[element.downcase] = key  
        end
    end
    return output
  end

end
