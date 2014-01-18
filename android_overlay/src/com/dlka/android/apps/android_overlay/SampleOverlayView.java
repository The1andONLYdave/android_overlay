package com.dlka.android.apps.android_overlay;

/*
Copyright 2011 jawsware international

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import android.view.Gravity;
import android.widget.TextView;

import com.jawsware.core.share.OverlayService;
import com.jawsware.core.share.OverlayView;

public class SampleOverlayView extends OverlayView {
	public int timer=3;
	
	private TextView info;
	
	public SampleOverlayView(OverlayService service) {
		super(service, R.layout.overlay, 1);
	}

	public int getGravity() {
		return Gravity.TOP + Gravity.RIGHT;
	}
	
	@Override
	protected void onInflateView() {
		info = (TextView) this.findViewById(R.id.textview_info);
		info.setText("Pause\nBenachrichtigung anklicken zum beenden.\n App neustarten für neue Pause einstellen.");
	}


	
}
