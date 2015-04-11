# -*- coding: UTF-8 -*-
# We are going to do the Transform step of an Extract-Transform-Load.
class ETL
  def self.transform(old)
    score = {}
    old.each do |key, value|
      value.each { |x| score[x.downcase] = key }
    end
    score
  end
end
