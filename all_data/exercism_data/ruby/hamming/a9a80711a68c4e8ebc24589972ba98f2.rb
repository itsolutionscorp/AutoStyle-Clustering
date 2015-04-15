# = hamming.rb
# Author::     Ricardo Gonzalez Saldivar
# Web::        rigsald.net
#
# == Hamming Class
# _Hamming_ class was designed during the
# exercism.io exercises.
class Hamming
  def self.compute(strand1, strand2)
    i = 0
    total = 0
    strand1.each_char do |c|
      if strand2[i] != nil
        total += 1 if c != strand2[i]
        i += 1
      end
    end
    return total
  end
end
