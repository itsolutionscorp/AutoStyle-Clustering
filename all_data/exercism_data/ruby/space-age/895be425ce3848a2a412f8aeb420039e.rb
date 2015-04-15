class SpaceAge
  def initialize(s)
    @seconds = s
  end

  attr_reader :seconds

  def on_mercury; convert(7600543.81992) end
  def on_venus;   convert(19414149.052176) end
  def on_earth;   convert(31557600) end
  def on_mars;    convert(59354032.690079994) end
  def on_jupiter; convert(374355659.124) end
  def on_saturn;  convert(929292362.8848) end
  def on_uranus;  convert(2651370019.3296) end
  def on_neptune; convert(5200418560.032001) end

  def convert(f)
    (@seconds.to_f / f).round(2)
  end

  private :convert
end
