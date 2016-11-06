package com.geek.firstaid.login.checks;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Circle;
import su.levenetc.android.textsurface.animations.Parallel;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Direction;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.Side;

/**
 * Created by Eugene Levenetc.
 */
public class CookieThumperSample {

	public static void play(TextSurface textSurface, AssetManager assetManager) {

		final Typeface robotoBlack = Typeface.createFromAsset(assetManager, "fonts/Roboto-Black.ttf");
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTypeface(robotoBlack);

/*		Text textDaai = TextBuilder
				.create("First")
				.setPaint(paint)
				.setSize(90)
				.setAlpha(0)
				.setColor(Color.WHITE)
				.setPosition(Align.SURFACE_CENTER).build();

		Text textBraAnies = TextBuilder
				.create("Aid")
				.setPaint(paint)
				.setSize(80)
				.setAlpha(0)
				.setColor(Color.GREEN)
				.setPosition(Align.RIGHT_OF, textDaai).build();
		*/

		Text textnull = TextBuilder
				.create(" ")
				.setPaint(paint)
				.setSize(120)
				.setAlpha(0).setColor(Color.WHITE)
				.setPosition(Align.SURFACE_CENTER).build();

		Text textFirst = TextBuilder
							.create("First")
							.setPaint(paint)
							.setSize(90)
							.setAlpha(0).setColor(Color.WHITE)
							.setPosition(Align.RIGHT_OF,textnull).build();


		Text textAid = TextBuilder
						.create("Aid")
						.setPaint(paint)
						.setSize(100)
						.setAlpha(0).setColor(Color.RED)
						.setPosition(Align.RIGHT_OF,textFirst).build();

		textSurface.play(
				new Sequential(
						/*ShapeReveal.create(textDaai, 750, SideCut.show(Side.LEFT), false),
						new Parallel(ShapeReveal.create(textDaai, 600, SideCut.hide(Side.LEFT), false), new Sequential(Delay.duration(300), ShapeReveal.create(textDaai, 600, SideCut.show(Side.LEFT), false))),
						new Parallel(TransSurface.toCenter(textBraAnies, 500), Rotate3D.showFromSide(textBraAnies, 750, Pivot.TOP)),*/


						/*ShapeReveal.create(textFirst, 750, SideCut.show(Side.LEFT), false),
						Delay.duration(500),
						new Parallel(TransSurface.toCenter(textAid, 600), Rotate3D.showFromSide(textAid, 750, Pivot.BOTTOM)),

						Delay.duration(200)*/

						new Parallel(
								new TransSurface(1500, textFirst, Pivot.CENTER|Pivot.RIGHT),
								new Sequential(
										new Sequential(ShapeReveal.create(textFirst, 500, Circle.show(Side.LEFT, Direction.OUT), false)),
										new Sequential(ShapeReveal.create(textnull, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
										new Sequential(ShapeReveal.create(textnull, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
										new Sequential(ShapeReveal.create(textnull, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
										new Sequential(ShapeReveal.create(textAid, 500, Circle.show(Side.RIGHT, Direction.OUT), false))
										/*new Sequential(ShapeReveal.create(textSignsInTheAir, 500, Circle.show(Side.CENTER, Direction.OUT), false))*/
								)
						)


				)
		);

	}

}
