require 'date'

class Gigasecond
  def self.from(d)
    # add 1Gs to the epoch time
    gs_epoch = d.strftime('%s').to_i + (10**9)

    # Parse and return
    Date.strptime(gs_epoch.to_s, '%s')
  end
end
