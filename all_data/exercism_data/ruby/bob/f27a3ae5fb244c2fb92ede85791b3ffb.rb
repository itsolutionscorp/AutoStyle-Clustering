module GratitudeRubyists
  class MailAuthors
  	@gem_file_path=File.expand_path('../Gemfile', __FILE__) || ENV['BUNDLE_GEMFILE']  ||  Dir.pwd + "/Gemfile"
        @gem_details_lists=[]

    def self.send_the_mail(user_email,project_flag=false)
  	  self.get_list_of_gem(@gem_file_path,user_email,project_flag)
    end
  
    #Todo how to pass if gem has more than one author ??
    def self.get_list_of_gem(gem_file_path,user_email,project_flag)
  		File.open(gem_file_path).read.each_line do |i|
  			i=i.split
    	  if i[0] == "gem" && i[0].start_with?("gem")
    			name = i[1].gsub(",","") 
    			gem_name= eval name
    			author_email_id=Gem::Specification.find_by_name(gem_name.to_s).email
    			author_name=Gem::Specification.find_by_name(gem_name).author
          project_name=project_flag==true ? true : nil
          project_name="test"  if project_name  #should i send the project name ??
          @gem_details_lists << { author_email: author_email_id,
                                  author_name: author_name ,
                                  user_email: user_email , 
                                  gem_name: gem_name ,
                                  project_name: project_name
                                }
          self.send_data(@gem_details_lists)
    		else
    			next
    		end
  			
  		end
	  end

    def self.send_data(gem_details_lists)
      @gem_details_lists.each do |i|
        EventMachine.run {
          http = EventMachine::HttpRequest.new('http://localhost:3000/api/data/').post :body => i
            http.callback {
              EventMachine.stop
            }
        }
      end
    end

  end
end
