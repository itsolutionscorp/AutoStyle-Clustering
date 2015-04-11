#!/usr/bin/env ruby

# Exercism 23
# ETL - Extract Transform Load

class ETL
  def self.transform(input)
    result = {}

    input.each do |num, letters|
      letters.each { |char| result[char.downcase] = num }
    end

    result
  end
end
