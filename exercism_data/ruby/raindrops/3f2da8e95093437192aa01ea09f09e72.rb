class Fizzboid
  Identity = ->(a) { a }
  ToS      = proc(&:to_s)

  def initialize(mappings = {}, fallback = Identity)
    @mappings = mappings.to_a
    @fallback = fallback
  end

  def convert(n)
    sub = @mappings.
      select{|(f,_)| f[n] }.
      map{|(_,e)| e }.
      join
    if sub.empty?
      @fallback[n]
    else
      sub
    end
  end

end

Raindrops = Fizzboid.new({
  ->(n) { n % 3 == 0 } => "Pling",
  ->(n) { n % 5 == 0 } => "Plang",
  ->(n) { n % 7 == 0 } => "Plong",
}, Fizzboid::ToS)
