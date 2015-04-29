class SpaceAge
  attr_reader :seconds

  def initialize seconds
    @seconds = seconds
  end
  
  def method_missing sym, *args, &block
    if RE =~ sym.to_s then (@seconds/ORBITAL_PERIODS[$1.to_sym]).round(2)
    else super
    end
  end

  def respond_to? sym, include_private = false
    if RE =~ sym.to_s then true
    else super
    end
  end
  
  private 
    ORBITAL_PERIODS = {
      earth: 31557600.0,
      mercury: 7600544.0,
      venus: 19414149.0,
      mars: 59354033.0,
      jupiter: 374355659.0,
      saturn: 929307574.0,
      uranus: 2651370019.0,
      neptune: 5200418560.0
    }
    RE = Regexp.new "^on_(#{ORBITAL_PERIODS.keys.map(&:to_s).join('|')})$"
end
