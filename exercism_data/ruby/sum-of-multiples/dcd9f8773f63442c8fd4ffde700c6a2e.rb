class SumOfMultiples
  def initialize *args
    @multipliers = args
  end

  def self.to border
    SumOfMultiples.new(3,5).to(border)
  end

  def to border
    @terms = {}
    @multipliers.min.upto(border-1).each{|i| @terms[i] = false}
    @multipliers.each{|m| (m..border-1).step(m){|term| @terms[term] = true}}
    @terms.select{|k, v| v == true }.keys.reduce(0){|acc, term| acc+term}
  end
end
