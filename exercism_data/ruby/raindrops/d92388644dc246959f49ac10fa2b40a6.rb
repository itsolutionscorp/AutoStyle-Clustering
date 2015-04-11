class Raindrops
  def self.convert(in_num)
    out_str = ''
    out_str+='Pling' if in_num%3 === 0
    out_str+='Plang' if in_num%5 === 0
    out_str+='Plong' if in_num%7 === 0
    out_str=in_num if out_str === ''
    out_str
  end
end
