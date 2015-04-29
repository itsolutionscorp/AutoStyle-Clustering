#!/usr/bin/env ruby
# encoding: utf-8
# Etl
class ETL
  def self.transform(oldData)
    # Define a global variable HASH
    @result_hash = {}
    oldData.keys.each do |k|
      value_array = oldData[k]
      value_array.each do|letterUpper|
        @result_hash[letterUpper.downcase] = k
      end
    end
    @result_hash
  end
end
