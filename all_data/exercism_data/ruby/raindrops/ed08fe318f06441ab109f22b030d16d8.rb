class Raindrops
  def self.convert(n)
    'iao'.each_char.with_index.reduce(nil) do |memo, (val, i)|
      (n % (i*2+3)).zero? ? memo.to_s << "Pl#{val}ng" : memo
    end || n.to_s
  end
end
