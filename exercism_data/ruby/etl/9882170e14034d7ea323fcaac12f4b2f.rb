#!/usr/bin/env ruby
# encoding: utf-8
# clase para convertir
class ETL
  def self.transform(old)
    salida = {}
    old.each do |key, valor|
      valor.each do |v|
        salida[v.downcase] = key
      end
    end
    salida
  end
end
