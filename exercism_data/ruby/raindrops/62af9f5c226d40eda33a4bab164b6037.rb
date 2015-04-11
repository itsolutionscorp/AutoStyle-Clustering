class Raindrops
  @@num_hash = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }
  def self.convert(in_num)
    out_str = ''
    @@num_hash.map {|n,s| out_str += s if in_num%n == 0}
    out_str == '' ? in_num.to_s : out_str
  end
end
