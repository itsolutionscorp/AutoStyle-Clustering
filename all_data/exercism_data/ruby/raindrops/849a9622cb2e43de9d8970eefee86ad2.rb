class Raindrops
  class << self
    def convert(num)
      @return_strings||=Hash.new { |h, key| h[key]=self.build(key) }
      @return_strings[num]
    end

    protected
    def build(num)
      return_string=''
      return_string << 'Pling' if num % 3 == 0
      return_string << 'Plang' if num % 5 == 0
      return_string << 'Plong' if num % 7 == 0
      return_string << num.to_s if return_string.empty?
      return_string
    end
  end
end
