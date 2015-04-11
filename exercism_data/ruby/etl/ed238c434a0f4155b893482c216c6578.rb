module ETL
  extend self

  def transform(input)
    input.
      invert.
      each_with_object({}) do |(data, source), db|
        data.each {|datum| db[datum.downcase] = source}
      end
  end
end
